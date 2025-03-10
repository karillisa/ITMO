import React, { useContext, useState, useEffect } from "react";
import { DotsFormContext } from "../InputFileds/InputFieldContext";

function DotsTable() {
    const context = useContext(DotsFormContext);

    const [currentPage, setCurrentPage] = useState(1);
    const rowsPerPage = 10; // Number of rows to display per page

    // Check if context?.getDots is defined, and reverse it only if it is
    const reversedDots = context?.getDots ? context.getDots.slice().reverse() : [];

    const indexOfLastDot = currentPage * rowsPerPage;
    const indexOfFirstDot = indexOfLastDot - rowsPerPage;
    const currentDots = reversedDots.slice(indexOfFirstDot, indexOfLastDot);

    // Pagination logic
    const totalPages = Math.ceil(reversedDots.length / rowsPerPage);

    const handlePageChange = (pageNumber: number) => {
        setCurrentPage(pageNumber);
    };

    useEffect(() => {
        // When the component mounts or when dots change, reset to the first page
        setCurrentPage(1);
    }, [context?.getDots]);

    return (
        <>
            <table className="w-full text-sm text-left rtl:text-right text-black">
                <thead className="text-md text-center text-gray-700 uppercase">
                <tr>
                    <th className="px-5 py-3">X</th>
                    <th className="px-5 py-3">Y</th>
                    <th className="px-5 py-3">R</th>
                    <th className="px-5 py-3">Result</th>
                    <th className="px-5 py-3 hidden lg:table-cell">Script time</th>
                    <th className="px-5 py-3 hidden lg:table-cell">Current time</th>
                </tr>
                </thead>
                <tbody>
                {currentDots && currentDots.length > 0 && currentDots.map((dot, index) => (
                    <tr key={index} className="bg-white border-b hover:bg-gray-50 text-center">
                        <td className="px-5 py-4">{dot.x}</td>
                        <td className="px-5 py-4">{dot.y}</td>
                        <td className="px-5 py-4">{dot.r}</td>
                        <td className={`px-5 py-4 font-semibold uppercase ${dot.status ? "text-green-700" : "text-red-700"}`}>
                            {dot.status ? "hit" : "miss"}
                        </td>
                        <td className="px-5 py-4 hidden lg:table-cell">{dot.scriptTime ? (dot.scriptTime / 1000).toFixed(0) : "..."}</td>
                        <td className="px-5 py-4 hidden lg:table-cell">{dot.time}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            {/* Pagination controls */}
            <div className="flex justify-center mt-4">
                <button
                    onClick={() => handlePageChange(currentPage - 1)}
                    disabled={currentPage === 1}
                    className="px-4 py-2 border rounded-md text-gray-700 disabled:text-gray-300"
                >
                    Previous
                </button>
                <span className="mx-2">{`Page ${currentPage} of ${totalPages}`}</span>
                <button
                    onClick={() => handlePageChange(currentPage + 1)}
                    disabled={currentPage === totalPages}
                    className="px-4 py-2 border rounded-md text-gray-700 disabled:text-gray-300"
                >
                    Next
                </button>
            </div>
        </>
    );
}

export default DotsTable;
